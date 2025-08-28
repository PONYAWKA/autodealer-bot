require: modules.js
    type = scriptEs6
    name = modules

init:
    bind("onMessage", function() {
        try {
            const cleaned = modules.preprocessText($request.text || "");
            if (cleaned) {
                $request.text = cleaned;
            }
            // Глобальные слоты
            if (!$session.service) $session.service = {};
        } catch (e) {}
    });
    bind("onAnyError", function() {
        $reactions.answer("Извините, произошла техническая ошибка. Пожалуйста, напишите в чат позже.");
    });

theme: /

    state: Start
        q!: $regex</start>
        script:
            $jsapi.startSession();
        a: Здравствуйте! Я бот автосервиса. Помогу записаться на техобслуживание и отвечу на вопросы.
        a: Чтобы оформить заявку на ТО, напишите, например: "запиши меня на техобслуживание".