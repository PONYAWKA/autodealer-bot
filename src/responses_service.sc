require: modules.js
    type = scriptEs6
    name = modules

theme: /service

    # Ответные стейты сценария записи на ТО

    state: a_service_collect_or_confirm
        script:
            const params = modules.extractParams($request.text || "");
            if (params.fio && !$session.service.fio) $session.service.fio = params.fio;
            if (params.phone && !$session.service.phone) $session.service.phone = params.phone;
            if (params.car && !$session.service.car) $session.service.car = params.car;
            const known = [!!$session.service.fio, !!$session.service.phone, !!$session.service.car].filter(Boolean).length;
            if (known >= 2) {
                $reactions.go("a_service_confirm");
            } else {
                $reactions.go("a_service_ask_missing");
            }

    state: a_service_ask_missing
        script:
            const missing = [];
            if (!$session.service.fio) missing.push('ФИО');
            if (!$session.service.phone) missing.push('номер телефона');
            if (!$session.service.car) missing.push('марка автомобиля');
            if (missing.length === 0) {
                $reactions.go("a_service_confirm");
            }
        a: Давайте оформим заявку на ТО. Пожалуйста, укажите недостающие данные.
        a: Недостаёт: {{($session.service.fio?"":"ФИО ") + ($session.service.phone?"":"номер телефона ") + ($session.service.car?"":"марка автомобиля")}}

    state: a_service_save_name
        script:
            const name = modules.normalizeFio($request.text||"");
            if (name) $session.service.fio = name;
        a: Принял. Укажите номер телефона в формате 79XXXXXXXXX или марку автомобиля.

    state: a_service_save_phone
        script:
            const phone = modules.extractPhone($request.text||"");
            if (phone) $session.service.phone = phone;
        a: Спасибо. Осталось указать ФИО или марку автомобиля.

    state: a_service_save_brand
        script:
            const car = modules.normalizeCarBrand($request.text||"");
            if (car) $session.service.car = car;
        a: Отлично. Укажите ещё ФИО или номер телефона.

    state: a_service_confirm
        a: Оформляю заявку на техобслуживание на следующие данные:
        a: {{ $session.service.fio || "—" }}
        a: номер телефона {{ $session.service.phone || "—" }}
        a: автомобиль {{ $session.service.car || "—" }}.
        a: Наш сотрудник свяжется с вами и уточнит время.
        script:
            // Очистим сессию для следующей заявки
            $session.service = {};


