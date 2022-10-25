package store.data.models;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Vendor extends User{
    private String storeName;
    private Set<String> storeAddress = new HashSet<>();
}
