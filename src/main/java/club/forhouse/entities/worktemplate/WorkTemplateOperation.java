package club.forhouse.entities.worktemplate;

import club.forhouse.entities.operation.Operation;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "works_templates_operations")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class WorkTemplateOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_id")
    @NonNull
    private Long rowId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    @ToString.Exclude
    @NonNull
    private WorkTemplate templateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operation_id")
    @NonNull
    @ToString.Exclude
    private Operation operationId;

    private int quantity = 0;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "operationId")
    @ToString.Exclude
    private List<WorkTemplateMaterial> materials;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkTemplateOperation operation = (WorkTemplateOperation) o;

        return rowId.equals(operation.rowId);
    }

    @Override
    public int hashCode() {
        return rowId.hashCode();
    }
}
