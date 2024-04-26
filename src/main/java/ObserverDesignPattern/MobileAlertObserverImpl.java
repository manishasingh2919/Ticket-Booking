package ObserverDesignPattern;

public class MobileAlertObserverImpl implements NotificationAlertObserver {
    StockObservable observable;
    String userName;

    public MobileAlertObserverImpl(StockObservable observable, String userName) {
        this.observable = observable;
        this.userName = userName;
    }

    @Override
    public void update() {
        msgSendOnMobile(userName, "Hurry up the sock is back, alert to ");
    }

    public void msgSendOnMobile(String userName, String msg) {
        System.out.println(msg + userName);
    }
}
