package org.kane.plugin.alert;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class Alert extends CordovaPlugin {
	
  protected void pluginInitialize() {}

  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
	  
    if (action.equals("alert")) {
    	
      alert(args.getString(0), args.getString(1), args.getString(2), callbackContext);
      
      return true;
      
    }
    
    return false;
    
  }

  /**
   * 生成一个原生的 Alert Dialog
   * 
   * @param title
   * @param message
   * @param buttonLabel
   * @param callbackContext
   */
  private synchronized void alert(final String title, final String message, final String buttonLabel, final CallbackContext callbackContext) {
	  
    new AlertDialog.Builder(cordova.getActivity())
    			   .setTitle(title)
    			   .setMessage(message)
    			   .setCancelable(false)
    			   .setNeutralButton(buttonLabel, new AlertDialog.OnClickListener() {
                      public void onClick(DialogInterface dialogInterface, int which) {
    	  		            dialogInterface.dismiss();
                            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
                      }      
                    }).create().show();
    
  }

}