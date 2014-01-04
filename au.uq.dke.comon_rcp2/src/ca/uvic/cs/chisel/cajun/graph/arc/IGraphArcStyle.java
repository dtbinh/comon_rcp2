package ca.uvic.cs.chisel.cajun.graph.arc;

import java.awt.Paint;
import java.awt.Stroke;

import ca.uvic.cs.chisel.cajun.graph.IGraphItemStyle;

/**
 * Defines the colors and strokes for graph arcs.
 *
 * @author Chris
 * @since  8-Nov-07
 */
public interface IGraphArcStyle extends IGraphItemStyle {

	/**
	 * Returns the stroke for the arc.
	 * @return the {@link Stroke}, can be null in which case the arc won't be drawn
	 */
	public Stroke getStroke(IGraphArc arc);
	
	/**
	 * Returns the color/paint for the arc.
	 * @return the {@link Paint}, can be null in which case the arc won't be drawn
	 */
	public Paint getPaint(IGraphArc arc);

	
}
