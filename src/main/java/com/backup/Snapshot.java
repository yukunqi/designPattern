package com.backup;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-05 22:50
 **/
public class Snapshot {
    private final long createTimeStamp;
    private final Object data;
    private final String version;
    private final Operator operator;

    public Snapshot(Operator operator,Object data) {
        this.createTimeStamp = System.currentTimeMillis();
        this.data = data;
        this.operator = operator;
        this.version = String.valueOf(createTimeStamp);
    }

    public Snapshot(String [] components) {
        if(components == null || components.length != 3){
            throw new IllegalArgumentException();
        }

        this.createTimeStamp = Long.parseLong(components[0]);
        this.operator = Operator.valueOf(components[1]);
        this.data = components[2];
        this.version = components[0];
    }

    public Snapshot(long createTimeStamp, Object data, Operator operator) {
        this.createTimeStamp = createTimeStamp;
        this.data = data;
        this.version = String.valueOf(createTimeStamp);
        this.operator = operator;
    }


    public void deal(StringBuilder stringBuilder){
        if (operator == Operator.Add){
            stringBuilder.append(data);
        }else if (operator == Operator.Del){
            String str = String.valueOf(data);
            stringBuilder.delete(stringBuilder.length() - str.length(),stringBuilder.length());
        }
    }



    public long getCreateTimeStamp() {
        return createTimeStamp;
    }

    public String toOperatorString(){
        return createTimeStamp + "\t"+ operator.name() + "\t" + data.toString();
    }
}
