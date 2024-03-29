package pl.university.project.populators.impl;

import pl.university.project.models.Client;
import pl.university.project.odata.ClientData;
import pl.university.project.populators.Populator;

public class ClientReversPopulator implements Populator<ClientData, Client> {
    @Override
    public void populate(ClientData source, Client target) {
        target.setId(source.getId());
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setJob(source.getJob());
        target.setPhoneNumber(source.getPhoneNumber());
        target.setContactType(source.getContactType());
        target.setAge(source.getAge());
        target.setMartialStatus(source.getMartialStatus());
        target.setEducationLevel(source.getEducationLevel());
        target.setHasDefaultCredit(source.getHasDefaultCredit());
        target.setHasMortgage(source.getHasMortgage());
        target.setHasConsumerCredit(source.getHasConsumerCredit());
        target.setBalance(source.getBalance());
    }
}
