theme: /faq

    # Запросные стейты FAQ

    state: q_faq_hours
        q!: * {время работы|режим работы|когда вы работаете}
        do: -> a_faq_hours

    state: q_faq_price_to
        q!: * {сколько стоит|стоимость|цена} * {то|техобслуживание}
        do: -> a_faq_price_to

    state: q_faq_location
        q!: * {где находитесь|адрес|как доехать}
        do: -> a_faq_location


