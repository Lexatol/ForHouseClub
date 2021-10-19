package club.forhouse.entities.operation;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "operations")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operation_id")
    @NonNull
    private Long operationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private OperationCategory category;

    private String name;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operation operation = (Operation) o;

        return operationId.equals(operation.operationId);
    }

    @Override
    public int hashCode() {
        return operationId.hashCode();
    }
}
