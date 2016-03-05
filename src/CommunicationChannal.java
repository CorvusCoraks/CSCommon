/**
 * Created by GreMal on 01.03.2016.
 */

    /*
    * - проверяем флаг команды. Команда должна быть false. Если команда true, значит, возможно, другой процесс работает с командой
    * - если команда - false, устанавливаем её в true, сигнализируя Core, о запросе данных или действий
    * - проверяем флаг ответа Core, о том, что запрос выполнен
    * - считываем данные из временной переменной
    * - устанавливаем флаг занятости канала в false
    * - устанавливаем флаг ответа Core в false
    * - устанавливаем флаг запроса в false, освобождая канал
    *
    * - для вызова из Core предназначены исключительно функции public
    *
    * Core сначала проверяет флаг "Делай", а потом флаг "Канал занят". Если канал занят, значит данная сессия связи занята другим процессом.
    *
    * Со стороны Core работа с этим объектом должна быть в одном synchronized блоке, чтобы другие процессы не могли влезть
    * */

// Если CommunicationChannal == null, то действия не требуются. Если CommunicationChannal != null, то действия требуются
// То есть, отдельный флаг-команда не требуются

public class CommunicationChannal {
    private volatile boolean commandGo = false; // Команда на запуск коммуникации
    private volatile boolean commandRunning = false; // Канал занят, устанавливает и снимает UI.
    private volatile boolean commandReady = false; // Данные переданы. Устанавливает Core, снимает UI
    private volatile Object answer = null; // ответ Core
    private volatile Class objectClass = null;

    // Для Core, проверка на занятость канала
    protected synchronized boolean isCommandRunning() {
        return commandRunning;
    }

    // Для Core. Установить метку, что канал занят.
    public synchronized void setCommandRunningTrue() {
        this.commandRunning = true;
    }

    // Для UI. Проверка. Исполнил ли Core команду
    protected synchronized boolean isCommandReady() {
        return commandReady;
    }

    // Для Core. Установить флаг, что Core задание выполнил. UI снимает флаг
/*    public synchronized void setCommandReadyTrue() {
        this.commandReady = true;
    }*/

    // Для UI. Получить объект ответа Core
    protected synchronized Object getAnswer() {
        // Очищаем флаги и возвращаем результат
        synchronized (this) {
            Object result = this.answer;
            this.commandGo = false;
            this.commandRunning = false;
            this.commandReady = false;
            this.answer = null;
            return result;
        }
    }

    // Для Core. Дать ответ на запрос UI. Если возвращает false - несовпадение типов
    public synchronized boolean setAnswer(Object answer) {
        synchronized (this) {
            if (this.objectClass.equals(answer.getClass())) {
                // если классы ожидаемого и присланного объекта совпадают
                this.answer = answer;
                this.commandReady = true;
                return true;
            }
            return false; // если вдруг несовпадение типов
        }
    }

    public synchronized boolean isCommandGo() {
        return this.commandGo;
    }

    protected synchronized void setCommandGo(boolean value) {
        this.commandGo = value;
    }

/*    protected synchronized void clearCommunicationChannal(){
        synchronized (this){

        }
    };*/

    public CommunicationChannal(Class objectClass) { this.objectClass = objectClass; }
}
