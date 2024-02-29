package jdbc;

public class MapElement {
    private String elementId;
    private String elementName;
    private String elementLocation;

    public MapElement(String elementId, String elementName, String elementLocation) {
        this.elementId = elementId;
        this.elementName = elementName;
        this.elementLocation = elementLocation;
    }

    public MapElement() {
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementLocation() {
        return elementLocation;
    }

    public void setElementLocation(String elementLocation) {
        this.elementLocation = elementLocation;
    }

    @Override
    public String toString() {
        return "MapElement [elementId=" + elementId + ", elementName=" + elementName + ", elementLocation=" + elementLocation + "]";
    }
}

