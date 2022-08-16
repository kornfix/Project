package pl.university.project.populators.impl;

import pl.university.project.models.Client;
import pl.university.project.odata.ClientData;
import pl.university.project.populators.Populator;

public class DefaultClientPopulator implements Populator<Client, ClientData> {
    @Override
    public void populate(Client source, ClientData target) {
        target.setId(source.getId());
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setJob(source.getJob());
        target.setPhoneNumber(source.getPhoneNumber());
        target.setContactType(source.getContactType());
        target.setAge(source.getAge());
        target.setMartialStatus(source.getMartialStatus());
        target.setEducationLevel(source.getEducationLevel());
        target.setDefaultCreditStatus(source.getDefaultCreditStatus());
        target.setHasMortgage(source.getHasMortgage());
        target.setHasConsumerCredit(source.getHasConsumerCredit());
        target.setBalance(source.getBalance());
    }
}
