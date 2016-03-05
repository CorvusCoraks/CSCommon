import java.util.HashMap;
import java.util.Map;

/**
 * Created by GreMal on 07.02.2016.
 */
public interface UISide {
    /* Функция проверки связи для вызова из внешних модулей. Формирование статусной строки в GUI,
    запуск процедур активации и деактивации элементов GUI */
    public void setInternetConnectionStatuses(InternetConnectionMessage status);
    /* Функция читает планируемые параметры GUI из соответствующего Мэпа контроллера */
    public void setInitGUIParameters();
    // Просто тестовая функция. Не нужна в работе.
    //public void testFunction();
    /* чтение из файла данных локализации (язык интерфейса). Имеет делать общий файл локализации для всех UI? */
    //public void localisation();
    public boolean getReady();
    // Получить ссылку на текстовое поле с именем данного устройства
    public AbstractUIControl getThisDeviceTextField();
    //public void setThisDeveceTextField(String DeveceName);
    // Получить ссылку на кнопку "Сохранить" название данного устройства
    public AbstractUIControl getThisDeviceButton();
    // Получить массив ссылок на текстовые поля других устройств круга
    public AbstractUIControl[] getOtherCircleDevicesTextField();
    // получить массив ссылок на кнопки "Пригласить - Выгнать"
    public AbstractUIControl[] getOtherCircleDevicesButton();
    // получить ссылку на кнопку "Синхронизировать"
    //public JButton getSynchronisationButton();
    // Получить ссылку на текстовую область с заметкой
    public AbstractUIControl getNoteTextArea();
    // возвращает ссылку на текстовое поле по ссылке на спаренную кнопку
    public AbstractUIControl getTextPaired(AbstractUIControl button);
    // Ссылка на текстовое поле с пригласительным паролем
    public AbstractUIControl getInvitationTextField();
    // возвращает свободное текстовое поле, в GUI-таблице устройств круга
    public AbstractUIControl getFreeOtherDeviceTextField() throws InterruptedException;
    // возвращает ссылку на кнопку по парному текстовому полю
    public AbstractUIControl getButtonByTextField(AbstractUIControl auic);
    // возвращает ссылку на текстовое поле, по парной кнопке
    //public JTextField getTextFieldByButton(JButton btn);
    // инвертируем текст кнопок Kick/Invite
    public void invertTextOnButton(AbstractUIControl auic);
    /*    Чистка свободного текстового поля в GUI-массиве устройств круга и, если на парной кнопке осталась старая
        надпись Kick, меняем её на Invite */
    public void clearFreeTextField() throws InterruptedException;
    /* Сделать доступным элементы окна программы. Реализация должна быть synchronized */
    //public void setFrameEnable();
    /* Сделать недоступными элементы окна программы. Реализация должна быть synchronized */
    //public void setFrameDisable();
    /* Функция возвращает статус активности окна приложения. Для поределения используется состояние noteArea */
    //public boolean isFrameEnabled();
    // вставить новый статус в массив статусов
    public void putNewStatusInStatusString(StatusSender sender, String status);
    // вставить новый статус в массив статусов, с указанием количества показов.
    // После указанного количества показов, статус удаляется из массива показов.
    //public void putNewStatusInStatusString(Tools.StatusSender sender, String status, int showCount);
    // удалить статус из массива статусов
    //public void removeStatusFromStatusString(Tools.StatusSender sender);
    public String getLocalisationValueByKey(String key);
    /* Установить максимальное количество членов круга */
    public void setMaxCompanyCount(int MaxCompanyCount);
    /* Установить максимальное количество символов в заметке */
    public void setMaxCharsInNote(int maxChars);
    /* Получить команду на закрытие приложения*/
    public boolean isCloseWindowCommand();
    /* Получить карту данных для сохранения в общем ini-файле программы */
    public Map<String, String> getIniDataMap();
    /* Скопировать ini-данные UI из общей ini-карты. То есть,
    на вход этой функции поступает ini-карта программы ПОЛНОСТЬЮ, со всеми данными
     */
    public void setIniDataMap(Map<String, String> iniMap);
    // Поставить UI на паузу или снять с паузы
    public void setUIPaused(boolean status);
    // установить статус изменения в поле заметки
    public void setIsNoteWasChanged(boolean status);
    // Получить статус изменений в поле заметки (было / небыло)
    public boolean getIsNoteWasChanged();
    // установить статус изменения в поле заметки
    public void setIsDeviceLabelWasChanged(boolean status);
    // Получить статус изменений в поле заметки (было / небыло)
    public boolean getIsDeveceLabelWasChanged();
    // получить команду "проснуться" для нити проверки связи с облаком
    public boolean getJerkThreadWakeUpCommand();
    // Нажата ли кнопка входа в круг?
    public boolean isEnterToCircleButtonPressed();
    // Core отработало нажатие кнопки входа в круг
    public void CoreOK_EnterToCircleButtonPressed();
    // Нажата ли кнопка "Синхронизировать"
    public boolean isStartSynchronizationCommand();
    // Core отработало запрос на синхронизацию
    public void CoreOK_StartSynchronizationCommand();
    // Нажата ли кнопка "Пригласить/Выгнать"
    // public boolean isInviteOrKickButtonPressed();
    /* Если возвращает null, но кнопка Пригласить/Выгнать НЕ нажата.
    В ином случае, функция возвращает интерфейс нажатой кнопки. */
    public AbstractUIControl InviteOrKickButtonPressed();
    // Core отработало нажатие клавиши Пригласить/Выгнать
    public void CoreOK_InviteOrKickButtonPressed();
    public void setCoreVersion(float ver);
    /* Передать в UI массив со ссылками на занятые текстовые поля членов круга */
    //public void setBusyTextFields(AbstractUIControl[] busyFields);
    // Получить запрос на выдачу массива со ссылками на знятые текстовые поля
    //public boolean getBusyFieldsCommand();\
    // Если CommunicationChannal == null, то действия не требуются. Если CommunicationChannal != null, то действия требуются
    // То есть, отдельный флаг-команда на исполнение не требуются
    public CommunicationChannal isSetBusyFieldsCommandActive();
}
