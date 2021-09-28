package club.forhouse.entities;

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
}
