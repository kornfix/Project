package pl.university.project.populators.impl;

import pl.university.project.models.Client;
import pl.university.project.odata.ClientData;
import pl.university.project.populators.Populator;

public class DefaultClientReversPopulator implements Populator<ClientData, Client> {
    @Override
    public void populate(ClientData source, Client target) {
        target.setId(source.getId());
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setPhoneNumber(source.getPhoneNumber());
        target.setAge(source.getAge());
        target.setMartialStatus(source.getMartialStatus());
        target.setEducation(source.getEducation());
        target.setDefaultCreditStatus(source.getDefaultCreditStatus());
        target.setHasMortgage(source.getHasMortgage());
        target.setHasConsumerCredit(source.getHasConsumerCredit());
        target.setBalance(source.getBalance());
    }
}
