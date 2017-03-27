import javax.swing.Icon;
import javax.swing.JLabel;

	 public class MyLabel extends JLabel {
		    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
			private String labelText;
		    private boolean labelTextVisible = true;

		    private MyLabel( String text, Icon icon, int horizontalAlignment ) {
		      super( text, icon, horizontalAlignment );
		      labelText = text;
		    }

		    private MyLabel( String text, int horizontalAlignment ) {
		      super( text, horizontalAlignment );
		      labelText = text;
		    }

		    private MyLabel( String text ) {
		      super( text );
		      labelText = text;
		    }

		    @Override
		    public void setText( String text ) {
		      if ( labelTextVisible ) {
		        super.setText( text );
		      }
		      labelText = text;
		    }

		    @Override
		    public String getText() {
		      return labelText;
		    }

		    public void setLabelTextVisible( boolean labelVisible ){
		      if(labelVisible){
		        if(!labelText.equals( super.getText() )){
		          super.setText( labelText );
		        }
		      }else{
		        int spaceCount = super.getText().length();
		        String hiddenText = "";
		        for ( int i = 0; i < spaceCount; i++ ) {
		          hiddenText+=" ";
		        }
		        super.setText(hiddenText);
		      }
		      this.labelTextVisible = labelVisible;
		    }

		    public boolean getLabelTextVisible(){
		      return labelTextVisible;
		    }
		  }


