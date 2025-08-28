theme: /service

    # Запросные стейты (классификация запросов на ТО)

    state: q_service_book_main
        q!: * {записаться|запиши|оформить} * {то|техобслуживание|тех обслуживание}
        q!: * {пройти|сделать} * {то|техобслуживание|тех обслуживание}
        q!: * {подошло|время} * {то|техобслуживание}
        do: -> a_service_collect_or_confirm

    # Входная точка, когда в запросе явно указана марка
    state: q_service_book_with_brand
        q!: * {то|техобслуживание|то-1|то1} *
        q!: * автомобиль *
        do: -> a_service_collect_or_confirm

    # Запросные стейты для уточняющих ответов пользователя (сбор параметров)
    state: q_service_provide_name
        q!: * {меня зовут|моё имя|мое имя|я} *
        do: -> a_service_save_name

    state: q_service_provide_phone
        q!: * {телефон|номер} *
        q!: * {+7|8|7} *
        do: -> a_service_save_phone

    state: q_service_provide_brand
        q!: * {авто|машина|марка|бренд} *
        do: -> a_service_save_brand


