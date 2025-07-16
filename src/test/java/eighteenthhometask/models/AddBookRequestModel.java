package eighteenthhometask.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddBookRequestModel {
    String userId;
    List<ISBNModel> collectionOfIsbns;
}
