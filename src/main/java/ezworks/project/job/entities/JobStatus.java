package ezworks.project.job.entities;

import lombok.Getter;

@Getter
public enum JobStatus {
    PENDING(1, "Pendiente"),
    MATCHED(2, "Emparejado"),
    FINISHED(3, "Finalizado");

    private final int value;
    private final String description;

    JobStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }
}