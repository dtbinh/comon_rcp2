package au.uq.dke.comon_rcp2.data.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.metawidget.inspector.annotation.UiHidden;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Trackable{
	
	
}
