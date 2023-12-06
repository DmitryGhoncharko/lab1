import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Cache {
    private List<Phone> phones = new ArrayList<>();
    private Cache(){

    }
    public List<Phone> getPhones(){
        return new ArrayList<>(phones);
    }
    public void add(Phone phone){
        phones.add(phone);
    }
    public boolean delete(String model){
        Optional<Phone> phoneOptional = phones.stream().filter(phone1 -> phone1.getModel().equals(model)).findFirst();
        if(phoneOptional.isPresent()){
           return phones.remove(phoneOptional.get());
        }
        return false;
    }
    public static Cache getInstance() {
        return Holder.INSTANCE;
    }
    private static class Holder{
        private static final Cache INSTANCE = new Cache();
    }
}
