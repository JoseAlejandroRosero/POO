package model;

public interface DrawingListener {
	
	public static enum DrawingEvent{
		ADDED,
		REMOVED,
		SELECTED,
		DESELECTED,
		MOVED,
		SAVED,
		LOADED,
		ADDED_TO_CLIPBOARD,
		PASTED,
		RESIZED,
		CHANGED_COLOR,
		CHANGED_FILLCOLOR,
		UNDOREDO, 
		NEW,
		GROUP,
		UNGROUP,
		CHANGE_THICKNESS
		//etc...
	}
	
	void update(DrawingEvent ev);

}
