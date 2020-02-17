package io.github.brenovit.store.models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name="permission")
@NoArgsConstructor
public class Permission {
	
	@Id
	@Basic
	private Long id;

	private String description;
	
	@Transient
	private EPermission permission;
	
	@PostLoad
	private void fillTransient() {
		if(id > 0) {
			this.permission = EPermission.of(id);
		}
	}	
}
