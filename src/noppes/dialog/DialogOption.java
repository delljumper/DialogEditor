package noppes.dialog;

import noppes.dialog.nbt.NBTTagCompound;

public class DialogOption {
	public int id;
	public int dialogId = -1;
	public String title = "Talk";
	public EnumOptionType optionType = EnumOptionType.Disabled;
	public int optionColor = 0xe0e0e0;
	public String command = "";
	
	public void readNBT(NBTTagCompound compound) {
		if(compound == null)
			return;
		title = compound.getString("Title");
		dialogId = compound.getInteger("Dialog");
		optionColor = compound.getInteger("DialogColor");
		optionType = EnumOptionType.values()[compound.getInteger("OptionType")];
		command = compound.getString("DialogCommand");
		if(optionColor == 0){
			optionColor = 0xe0e0e0;
		}
	}

	public NBTTagCompound writeNBT() {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setString("Title", title);
		compound.setInteger("OptionType", optionType.ordinal());
		compound.setInteger("Dialog", dialogId);
		compound.setInteger("DialogColor", optionColor);
		compound.setString("DialogCommand", command);
		return compound;
	}
	

	public boolean hasDialog(){
		if(dialogId <= 0)
			return false;
		if(!DialogController.instance.hasDialog(dialogId)){
			dialogId = -1;
			return false;
		}
		return true;
	}

	public Dialog getDialog() {
		if(!hasDialog())
			return null;
		return DialogController.instance.dialogs.get(dialogId);
	}

	public String toString(){
		return title + " : " + id;
	}
}
