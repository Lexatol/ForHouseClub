package club.forhouse.entities;

import lombok.*;

import javax.persistence.*;

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
