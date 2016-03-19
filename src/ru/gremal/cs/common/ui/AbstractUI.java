package ru.gremal.cs.common.ui;

import ru.gremal.cs.common.channel.CommunicationChannel;
import ru.gremal.cs.common.channel.SimpleCommunicationChannel;
import ru.gremal.cs.common.interfaces.CoreSide;
import ru.gremal.cs.common.interfaces.UISide;

import java.util.Map;
import java.util.logging.FileHandler;

/**
 * Created by GreMal on 27.02.2016.
 */
public abstract class AbstractUI implements UISide {
    // Количество устройств в круге. Устанавливается модулем Core путём вызова setMaxCompanyCount
    private int MAX_COMPANY_COUNT;
    // Максимальное количество символов в заметке
    private int MAX_CHARS_IN_NOTE;
    // версия ядра
    private float CORE_VERSION;
    // версия UI
    private float UI_VERSION;
    // статус готовности UI
    private boolean uiReady = false;
    // Ссылка на лог-файл
    private FileHandler logFileHandler;
    // ссылка на интерфейсный класс ядра программы
    protected CoreSide core;

    // Флаг-команда для Core, что приложение надо завершить (Core сохранит настройки и завершит работу)
    //private volatile boolean closeWindowCommand = false;
    //private volatile boolean closeWindowCommand = false;
    /* Подтверждение от Core, что UI можно таки закрыть. То есть, закрытие UI происходит в несколько этапов: пользователь, используя элемент управления, подаёт команду на закрытие UI (1); UI стопорится и передаёт запрос в Core на разрешение закрытия приложения (2); Core получает запрос, и производит необходимые действия по подготовке закрытия приложения (3); Core замораживается (в смысле запросов в UI) и передаёт в UI разрешение на закрытие приложения(4); UI, получив от Core разрешение на закрытие, размораживается, и проводит со своей стороны операции, необходимые для закрытия. И, закрывается (5); Core, определив, что UI=null, закрывается и сам(6) */
    //private SimpleCommunicationChannel closeWindowCommandAgree = new SimpleCommunicationChannel(false);
    // Карта параметров UI, которые подлежат сохранению в файле ini
    //private Map<String, String> iniData;
    // состояние работы UI, если нужно временно остановить поток GUI
    //private volatile boolean uiPaused = false;
    // Пользователь внёс изменения в поле заметки
    // private volatile boolean isNoteWasChangad = false;
    //private SimpleCommunicationChannel noteWasChanged = new SimpleCommunicationChannel(false);
    // Пользователь внёс изменения в поле имени любого устройства
    // private volatile boolean isDeviceLableWasChanged = false;
    //private SimpleCommunicationChannel deviceLableWasChangedChannal = new SimpleCommunicationChannel(false);
    // Маркер параметров UI в ini-файле и в ini-карте. Находится в начале имени (ключа) параметра.
    //final String UI_INI_DATA_MARKER = "UI_";
    // Команда проснуться и работать для нити проверки связи
    // private volatile boolean isJerkThreadRunning = true;
    //private SimpleCommunicationChannel jerkThreadWakeUpCommandChannal = new SimpleCommunicationChannel(true);
    // Нажата кнопка входа в круг
    // private volatile boolean isEnterToCircleButtonPressed = false;
    //private SimpleCommunicationChannel enterToCircleButtonPressedChannal = new SimpleCommunicationChannel(false);
    // Нажата ли кнопка "Синхронизация"
    //private SimpleCommunicationChannel startSynchronizationButtonPressedChannal = new SimpleCommunicationChannel(false);
    // Нажата кнопка приглашения/изгнания
    // private boolean isInviteOrKickButtonPressed = false;
    // Какая именно кнопка приглашения/изгнания нажата
    //private AbstractUIControl InviteOrKickButton = null;
    //private SimpleCommunicationChannel inviteOrKickButtonPressedChannal = new SimpleCommunicationChannel(AbstractUIControl.class, false);
    // Массив с занятыми текстовыми полями. Как только заказчик получит массив, этому полю присваивается null, а команде - false
    //private CommunicationChannel busyTextFieldsChannal = new CommunicationChannel(AbstractUIControl[].class);
    //private AbstractUIControl[] busyTextFields;
    // Команда для Core, выдать массив с занятыми текстовыми полями
    //private boolean getBusyTextFieldsCommand = false;
    /* Таймаут. Только спустя данный промежуток времени (миллисекунды) с моменда последнего изменения текстового поля,
    устанавливается флаг неких изменений данного поля (сигнал для Core)
    * */
    protected final int FIELD_CHANGE_TIMEOUT = 5000;

    @Override
    public void setMaxCompanyCount(int MaxCompanyCount) {
        this.MAX_COMPANY_COUNT = MaxCompanyCount;
    }

    protected int getMaxCompanyCount(){
        return this.MAX_COMPANY_COUNT;
    }

    @Override
    public void setMaxCharsInNote(int maxChars) {
        this.MAX_CHARS_IN_NOTE = maxChars;
    }

    protected int getMaxCharsInNote(){ return this.MAX_CHARS_IN_NOTE; }
/*
    @Override
    public boolean isCloseWindowCommand() {
        return closeWindowCommand;
    }

    protected synchronized void setCloseWindowCommand(boolean closeWindowCommand) {
        this.closeWindowCommand = closeWindowCommand;
    }
*/
    /*
    @Override
    public Map<String, String> getIniDataMap() {
        return iniData;
    }

    @Override
    public void setIniDataMap(Map<String, String> iniMap) {
        for(Map.Entry<String, String> pair : iniMap.entrySet()){
            String key = pair.getKey();
            String value = pair.getValue();
            if(key.substring(0, 3).equals("UI_")){ this.iniData.put(key, value); }
        }
    }

    protected void putIniDataInMap(String key, String value){
        iniData.put(key, value);
    }

    protected String getIniDataFromMap(String key){ return this.iniData.get(key); };
    */
/*
    @Override
    public synchronized void setUIPaused(boolean status) {
        // todo возможно, другой поток может установить этот флаг в true. Нужно защитить этот флаг, в данной ситуации, от изменения дргим потоком
        this.uiPaused = status;
    }

    protected boolean getUIPaused(){ return this.uiPaused; }
*/

    //@Override
    //public SimpleCommunicationChannel getNoteWasChangedChannal(){return this.noteWasChanged;}
/*
    @Override
    public synchronized void setIsNoteWasChanged(boolean changed) {
        this.isNoteWasChangad = changed;
    }

    @Override
    public boolean getIsNoteWasChanged() {
        return this.isNoteWasChangad;
    }
    */

    //@Override
    //public SimpleCommunicationChannel getDeviceLabelWasChangedChannal(){return this.deviceLableWasChangedChannal;}
/*
    @Override
    public boolean getIsDeveceLabelWasChanged() {
        return this.isDeviceLableWasChanged;
    }

    @Override
    public synchronized void setIsDeviceLabelWasChanged(boolean status) {
        this.isDeviceLableWasChanged = status;
    }
*/


    //@Override
    //public SimpleCommunicationChannel getJerkThreadWakeUpCommandChannal(){return this.jerkThreadWakeUpCommandChannal;}
/*
    @Override
    public boolean getJerkThreadWakeUpCommand() {
        return this.isJerkThreadRunning;
    }

    protected synchronized void setJerkThreadWakeUpCommand(boolean command){
        this.isJerkThreadRunning = command;
    }
    */

    //@Override
    //public SimpleCommunicationChannel getEnterToCircleButtonPressedChannal(){return this.enterToCircleButtonPressedChannal;}
/*
    @Override
    public synchronized void CoreOK_EnterToCircleButtonPressed() {
        this.isEnterToCircleButtonPressed = false;
    }

    @Override
    public boolean isEnterToCircleButtonPressed() {
        return this.isEnterToCircleButtonPressed;
    }

    protected synchronized void setEnterToCircleButtonPressed(boolean status){
        this.isEnterToCircleButtonPressed = status;
    }
*/

    //@Override
    //public SimpleCommunicationChannel getStartSynchronizationButtonPressedChannal(){return startSynchronizationButtonPressedChannal;}

    /*
    @Override
    public synchronized void CoreOK_StartSynchronizationCommand() {
        this.isStartSynchronizationButtonPressed = false;
    }

    @Override
    public boolean isStartSynchronizationCommand() {
        return this.isStartSynchronizationButtonPressed;
    }

    protected synchronized void setStartSynchronizationButtonPressed(boolean command){
        this.isStartSynchronizationButtonPressed = command;
    }
    */

    //@Override
    //public SimpleCommunicationChannel getInviteOrKickButtonPressedChannal() {return this.inviteOrKickButtonPressedChannal;}

    /*
    @Override
    public synchronized void CoreOK_InviteOrKickButtonPressed() {
        this.InviteOrKickButton = null;
    }

    @Override
    public AbstractUIControl InviteOrKickButtonPressed() {
        return this.InviteOrKickButton;
    }

    protected synchronized void setInviteOrKickButtonPressed(AbstractUIControl auic){ this.InviteOrKickButton = auic; }
*/
    //@Override
    protected void setCoreVersion(float ver) {
        this.CORE_VERSION = ver;
    }

    protected void setUIVersion(float ver){ this.UI_VERSION = ver; }

    protected float getCoreVersion(){ return this.CORE_VERSION; }

    protected float getUIVersion(){ return this.UI_VERSION; }

    //@Override
    //public CommunicationChannel getBusyTextFieldsCommandChannal() {return this.busyTextFieldsChannal;}

    //@Override
    //public SimpleCommunicationChannel getCloseWindowCommandAgreeChannal(){return this.closeWindowCommandAgree;}
    @Override
    public boolean getReady(){ return this.uiReady; }

    protected void setReady(boolean value){ this.uiReady = value;}

    protected FileHandler getLogFileHandler(){ return this.logFileHandler; }
    protected void setLogFileHandler(){ this.logFileHandler = core.getLogFileHandler(); }

    //protected CoreSide get
}
