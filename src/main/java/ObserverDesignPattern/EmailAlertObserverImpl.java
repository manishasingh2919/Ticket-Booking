package ObserverDesignPattern;

public class EmailAlertObserverImpl implements NotificationAlertObserver {
    StockObservable observable;
    String emailId;

    public EmailAlertObserverImpl(StockObservable observable, String emailId) {
        this.observable = observable;
        this.emailId = emailId;
    }

    @Override
    public void update() {
        sendEmail(emailId, "Hurry up the sock is back, mail send to ");
    }

    public void sendEmail(String emailID, String msg) {
        System.out.println(msg + emailID);
    }
}
