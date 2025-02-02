package id.dojo.helper;

public class Res<T> {
    private String message;
    private T data;

    public Res(String message, T data){
        this.message = message;
        this.data = data;
    }
    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
