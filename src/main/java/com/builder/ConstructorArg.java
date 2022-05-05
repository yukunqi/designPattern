package com.builder;

/**
 * 当 isRef 为 true 的时候，arg 表示 String 类型的 refBeanId，type 不需要设置；
 * 当 isRef 为 false 的时候，arg、type 都需要设置。
 * 请根据这个需求，完善 ConstructorArg 类。
 */
public class ConstructorArg {
    private boolean isRef;
    private Class<?> type;
    private Object arg;

    private ConstructorArg(){}

    public ConstructorArg(ConstructorArgBuilder builder){
        this.isRef = builder.isRef;
        this.type = builder.type;
        this.arg = builder.arg;
    }


    public static class ConstructorArgBuilder{
        private boolean isRef;
        private Class<?> type;
        private Object arg;


        public ConstructorArgBuilder setRef(boolean ref) {
            isRef = ref;
            return this;
        }

        public ConstructorArgBuilder setType(Class<?> type) {
            this.type = type;
            return this;
        }

        public ConstructorArgBuilder setArg(Object arg) {
            if (arg == null){
                throw new IllegalArgumentException("arg can not be null");
            }
            this.arg = arg;
            return this;
        }

        public ConstructorArg build(){

            if (isRef){
                if (!(arg instanceof String)){
                    throw new IllegalArgumentException("arg is not String when isRef is true");
                }

                if (type != null){
                    throw new IllegalArgumentException("don't set type parameter when isRef is true");
                }
            }

            if (!isRef && (type == null || arg == null)){
                throw new IllegalArgumentException("type or arg can not be null");
            }

            return new ConstructorArg(this);
        }
    }
}
