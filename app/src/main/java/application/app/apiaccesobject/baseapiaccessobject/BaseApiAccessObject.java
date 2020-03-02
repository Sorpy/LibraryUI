package application.app.apiaccesobject.baseapiaccessobject;

public interface BaseApiAccessObject <IN> {

    String postRequest(IN param);

    String getRequest();

    String deleteRequest();

    String putRequest();

}
