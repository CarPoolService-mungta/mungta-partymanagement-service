package partymanagement.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import partymanagement.PartyManagementApplication;
import partymanagement.domain.event.SelectedRole;
import partymanagement.domain.repository.PartyInfoRepository;
import partymanagement.domain.vo.*;
import partymanagement.domain.enumeration.*;

@Entity
@Table(name = "PartyInfo_table")
@Data
public class PartyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int curNumberOfParty;
    private int maxNumberOfParty;
    @Embedded
    private Driver driver;

    @Embedded
    private MoveInfo moveInfo;

    @ElementCollection
    private List<CarPooler> carPooler;

    private PartyStatus status;

    // @PostPersist
    // public void onPostPersist() {
    //     SelectedRole selectedRole = new SelectedRole(this);
    //     System.out.println("######################PartyInfo 저장!");
    //     System.out.println("id:"+id+" curNumberOfParty");
    //     selectedRole.publishAfterCommit();
    // }

    public static PartyInfoRepository repository() {
        PartyInfoRepository partyInfoRepository = PartyManagementApplication.applicationContext.getBean(
            PartyInfoRepository.class
        );
        return partyInfoRepository;
    }

    public void selectRole() {}
}
