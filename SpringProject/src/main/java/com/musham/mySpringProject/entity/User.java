package com.musham.mySpringProject.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


/**
 * we have to give collection name when the Pojo
 * class name is different from the collection name
 */
@Document(collection = "users")
/**
 * A Data annotation is a shortcut annotation that combines @Getter, @Setter,
 * @ToString, @EqualsAndHashCode, and @RequiredArgsConstructor.
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;

    @Indexed(unique = true) //This will not accept duplicate names
    /**
     * Eventhough after giving Indexed attribute SpringBoot will not do any indexing.
     * To enable this feature we have to give an entry in application.properties as:
     *  "spring.data.mongodb.auto-index-creation=true"
     */
    @NonNull    // Null constraint
    private String userName;

    @NonNull
    private String password;

    @DBRef  //This annotation is required to create reference to JournalEntry
    List<JournalEntry> journalEntries = new ArrayList<>();

    private List<String> roles;

}
