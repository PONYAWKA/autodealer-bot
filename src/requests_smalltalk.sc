theme: /smalltalk

    # Общекоммуникативные запросные стейты

    state: q_smalltalk_hello
        q!: * {привет|здравствуйте|добрый день|доброе утро|добрый вечер}
        do: -> a_smalltalk_hello

    state: q_smalltalk_thanks
        q!: * {спасибо|благодарю}
        do: -> a_smalltalk_thanks

    state: q_smalltalk_bye
        q!: * {пока|до свидания|до встречи}
        do: -> a_smalltalk_bye


