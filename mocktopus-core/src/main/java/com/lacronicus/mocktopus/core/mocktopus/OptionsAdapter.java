package com.lacronicus.mocktopus.core.mocktopus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.lacronicus.mocktopus.core.R;
import com.lacronicus.mocktopus.core.mocktopus.options.MethodFieldOption;
import com.lacronicus.mocktopus.core.mocktopus.options.method.MethodOption;
import com.lacronicus.mocktopus.core.mocktopus.options.observable.ObservableOption;
import com.lacronicus.mocktopus.core.mocktopus.view.ItemClassView;
import com.lacronicus.mocktopus.core.mocktopus.view.ItemFieldView;
import com.lacronicus.mocktopus.core.mocktopus.view.ItemMethodView;
import com.lacronicus.mocktopus.core.mocktopus.view.ItemOptionView;

/**
 * Created by fdoyle on 7/15/14.
 * todo make this better re. design, backing model, etc
 */
public class OptionsAdapter extends BaseExpandableListAdapter {
    FlattenedOptions options;
    Settings settings;

    Context c;
    LayoutInflater inflater;

    public OptionsAdapter(Context c) {
        this.c = c;
        inflater = LayoutInflater.from(c);
    }

    public void setContent(FlattenedOptions options, Settings settings) {
        this.options = options;
        this.settings = settings;
    }


    @Override
    public int getGroupCount() {
        if (options == null)
            return 0;
        return options.itemList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        switch (getGroupType(groupPosition)) {
            case FlattenedOptions.FlatOptionsItem.TYPE_METHOD:
                return getGroup(groupPosition).methodItem.methodOptions.size();
            case FlattenedOptions.FlatOptionsItem.TYPE_OBSERVABLE:
                return getGroup(groupPosition).observableObjectItem.observableOptions.size();
            case FlattenedOptions.FlatOptionsItem.TYPE_COLLECTION:
                return 0;//todo
            case FlattenedOptions.FlatOptionsItem.TYPE_CLASS:
                return 0;//todo
            case FlattenedOptions.FlatOptionsItem.TYPE_FIELD:
                return getGroup(groupPosition).methodFieldItem.fieldOptions.size();
            case FlattenedOptions.FlatOptionsItem.TYPE_INVALID:
            default:
                return 0;
        }
    }

    @Override
    public FlattenedOptions.FlatOptionsItem getGroup(int groupPosition) {
        return options.itemList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        FlattenedOptions.FlatOptionsItem item = options.itemList.get(groupPosition);
        switch (getGroupType(groupPosition)) {
            case FlattenedOptions.FlatOptionsItem.TYPE_METHOD:

                ItemMethodView methodView;
                if(convertView == null) {
                    methodView = (ItemMethodView) inflater.inflate(R.layout.mock_item_method, parent, false);
                } else {
                    methodView = (ItemMethodView) convertView;
                }
                methodView.text.setText(item.getString());

                return methodView;
            case FlattenedOptions.FlatOptionsItem.TYPE_OBSERVABLE:
            case FlattenedOptions.FlatOptionsItem.TYPE_COLLECTION:
            case FlattenedOptions.FlatOptionsItem.TYPE_CLASS:
                ItemClassView classView;
                if(convertView == null) {
                    classView = (ItemClassView) inflater.inflate(R.layout.mock_item_class, parent, false);
                } else {
                    classView = (ItemClassView) convertView;
                }
                classView.text.setText(item.getString());

                return classView;
            case FlattenedOptions.FlatOptionsItem.TYPE_FIELD:
                ItemFieldView fieldView;
                if(convertView == null) {
                    fieldView = (ItemFieldView) inflater.inflate(R.layout.mock_item_field, parent, false);
                } else {
                    fieldView = (ItemFieldView) convertView;
                }
                fieldView.text.setText(item.getString());

                return fieldView;
            case FlattenedOptions.FlatOptionsItem.TYPE_INVALID:
            default:
                return null;
        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        FlattenedOptions.FlatOptionsItem item = options.itemList.get(groupPosition);
        ItemOptionView optionView;//this is just a textview for all options, break it out into multiples if that ever becomes necessary
        if(convertView == null) {
            optionView = (ItemOptionView) inflater.inflate(R.layout.mock_item_option, parent, false);
        } else {
            optionView = (ItemOptionView) convertView;
        }
        FlattenedOptions.FlatOptionsItem group = getGroup(groupPosition);
        switch (group.getType()) {
            case FlattenedOptions.FlatOptionsItem.TYPE_METHOD: {
                MethodOption methodOption = group.methodItem.methodOptions.get(childPosition);
                optionView.text.setText(methodOption.getDescription());
                MethodOption currentOption = settings.getMethodOption(item.methodItem.method);
                if (methodOption.equals(currentOption)) {
                    optionView.setBackgroundColor(c.getResources().getColor(R.color.cyan200));
                } else {
                    optionView.setBackgroundColor(c.getResources().getColor(R.color.white));
                }
                break;
            }
            case FlattenedOptions.FlatOptionsItem.TYPE_OBSERVABLE: {
                //MethodFieldOption option = group.methodFieldItem.fieldOptions.get(childPosition);
                ObservableOption observableOption = group.observableObjectItem.observableOptions.get(childPosition);
                optionView.text.setText(observableOption.getDescription());
                ObservableOption currentOption = settings.getObservableOption(item.observableObjectItem.method);
                if (observableOption.equals(currentOption)) {
                    optionView.setBackgroundColor(c.getResources().getColor(R.color.cyan200));
                } else {
                    optionView.setBackgroundColor(c.getResources().getColor(R.color.white));
                }
                break;
            }
            case FlattenedOptions.FlatOptionsItem.TYPE_COLLECTION:
                break;
            case FlattenedOptions.FlatOptionsItem.TYPE_CLASS:
                break;
            case FlattenedOptions.FlatOptionsItem.TYPE_FIELD: {
                MethodFieldOption option = group.methodFieldItem.fieldOptions.get(childPosition);
                optionView.text.setText(String.valueOf(option));
                MethodFieldOption currentOption = settings.get(item.methodFieldItem.method, item.methodFieldItem.field);
                if (option.equals(currentOption)) {
                    optionView.setBackgroundColor(c.getResources().getColor(R.color.cyan200));
                } else {
                    optionView.setBackgroundColor(c.getResources().getColor(R.color.white));
                }
                break;
            }
            case FlattenedOptions.FlatOptionsItem.TYPE_INVALID:
            default:
                break;
        }
        return optionView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }



    @Override
    public int getGroupTypeCount() {
        return 5;

    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return super.getChildType(groupPosition, childPosition);
    }

    @Override
    public int getGroupType(int groupPosition) {
        return getGroup(groupPosition).getType();
    }

    @Override
    public int getChildTypeCount() {
        return super.getChildTypeCount();
    }

}
