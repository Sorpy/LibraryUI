package application.app.processor.accountprocessor;

import application.app.apiaccesobject.accountaccesobject.AccountAao;
import application.app.entity.param.AccountParam;
import application.app.entity.result.AccountResult;
import application.app.processor.baseprocessor.BaseProcessorImpl;
import org.springframework.stereotype.Component;

@Component
public class AccountProcessorImpl extends BaseProcessorImpl<AccountParam, AccountResult, AccountAao> implements AccountProcessor {
    @Override
    public Class<AccountResult> getMyType() {
        return AccountResult.class;
    }
}
