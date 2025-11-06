package br.com.dluzedesign.wood.dwoodbackend.exceptions;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

import java.text.MessageFormat;
import java.util.ResourceBundle;

@RequiredArgsConstructor
public class BaseErrorMessage {
    private final String DEFAULT_RESOURCE = "messages";


    public static final BaseErrorMessage GENERIC_EXCEPTION = new BaseErrorMessage("generic");
    public static final BaseErrorMessage GENERIC_NOT_FOUND = new BaseErrorMessage("generic.notFound");
    public static final BaseErrorMessage GENERIC_METHOD_NOT_ALLOW = new BaseErrorMessage("generic.methodNotAllow");
    public static final BaseErrorMessage GENERIC_BAD_REQUEST = new BaseErrorMessage("generic.badRequest");
    public static final BaseErrorMessage CATEGORY_NOT_FOUND = new BaseErrorMessage("category.NotFound");
    public static final BaseErrorMessage CATEGORY_ALREADY_EXISTS = new BaseErrorMessage("category.AlreadyExists");
    public static final BaseErrorMessage PRODUCT_NOT_FOUND = new BaseErrorMessage("product.NotFound");

    private final String key;

    private String[] params;
    public BaseErrorMessage params(final String ... params){
        this.params = ArrayUtils.clone(params);
        return this;
    }
    public String getMessage(){
        var message = tryGetMessageFromBundle();
        if(ArrayUtils.isNotEmpty(params)){
            final var fmt = new MessageFormat(message);
            message = fmt.format(params);
        }
        return message;
    }
    private String tryGetMessageFromBundle(){
        return getResouce().getString(key);
    }
    public ResourceBundle getResouce() {
        return ResourceBundle.getBundle(DEFAULT_RESOURCE);
    }
}
