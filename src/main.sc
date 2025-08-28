require: modules.js
    type = scriptEs6
    name = modules

require: patterns.sc
  module = sys.zb-common

init:
    bind("onAnyError", function() {
        $reactions.answer("Извините, произошла техническая ошибка. Пожалуйста, напишите в чат позже.");
    });

theme: /

    state: Start
        q!: $regex</start>
        script:
            $jsapi.startSession();
        a: Приветствую!
        a: Я бот-помощник. Могу кратко изложить текст из файла в формате PDF, PNG, JPEG, GIF, TIFF и BMP.