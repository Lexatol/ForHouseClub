package club.forhouse.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "works_templates")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class WorkTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id")
    @NonNull
    private Long templateId;

    private String name;
    private String description;

    @OneToMany(mappedBy = "templateId")
    private List<WorkTemplateMaterial> materials;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkTemplate that = (WorkTemplate) o;
        return templateId.equals(that.templateId);
    }

    @Override
    public int hashCode() {
        return templateId.hashCode();
    }
}
