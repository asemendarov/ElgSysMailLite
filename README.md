# ElgSysMailLite

Место расположение .jar и config (не release) [out/artifacts/ElgSysMailLite](https://github.com/oNoComments/ElgSysMailLite/tree/master/out/artifacts/ElgSysMailLite).

Для запуска jar файла необходимо:
1. Скпировать папку `out/artifacts/ElgSysMailLite` и встасить её в `%USERPROFILE%`, так чтобы в итоге получилось `%USERPROFILE%\ElgSysMailLite`;
2. Откройте командную строку для начала и введите команду `chcp 1251`;
3. Далее для запуска самой программы введите примерно следующую команду: `"C:\Program Files\Java\jdk-10.0.1\bin\java.exe" --add-modules=java.activation -Dfile.encoding=windows-1251 -jar %USERPROFILE%\ElgSysMailLite\ElgSysMailLite.jar`;
4. Все, программа запущена. Если возникли проблемы, то проверьте конфигурационные файлы.

Консольные команды:
1. `-help` — вызов справки;
2. `-stop` — принудительное завершение приложения.

Дополнительная информация:
1. Обратите внимание на конфигурационный файл `mail.properties`, по умолчанию в полном доменном имени службы SMTP написано `aspmx.l.google.com`, для нее действительные следующие правила: используемый порт - 25; SSL не требуется; авторизация не требуется; разрешены динамические IP-адреса; **сообщения можно отправлять только пользователям Gmail и G Suite** ([подробнее](https://support.google.com/a/answer/176600?hl=ru)).
2. Т.к. выше указанный SMTP могут использовать не авторизованные пользователи, то **все сообщения попадают в папку спам**.
3. Если вы пытаетесь собрать приложение через IDE, то обратите внимание на то, что вам, возможно, придется подключить библиотеку Java Mail ([resources/modules](https://github.com/oNoComments/ElgSysMailLite/tree/master/resources/modules)) и добавить дополнительные параметр для виртуальной машины `--add-modules=java.activation`.

Feedback: [Telegram](https://t.me/oNoComments).
