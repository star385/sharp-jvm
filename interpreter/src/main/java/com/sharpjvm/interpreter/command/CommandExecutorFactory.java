package com.sharpjvm.interpreter.command;

import com.sharpjvm.bytecode.bean.command.Command;
import com.sharpjvm.bytecode.constants.Constants;
import com.sharpjvm.interpreter.ExecuteException;

import java.util.HashMap;
import java.util.Map;

/**
 * 指令执行器工厂类。
 *
 * User: zhuguoyin
 * Date: 13-3-9
 * Time: 下午1:53
 * To change this template use File | Settings | File Templates.
 */
public class CommandExecutorFactory {

    private static Map<Byte, CommandExecutor> commandExecutorMap;

    static {
        commandExecutorMap = new HashMap<Byte, CommandExecutor>();
        CommandExecutor nopExecutor = new NopExecutor();
        commandExecutorMap.put(Constants.NOP, nopExecutor);
        commandExecutorMap.put(Constants.GET_STATIC, new GetStaticExecutor());
        ConstExecutor constExecutor = new ConstExecutor();
        commandExecutorMap.put(Constants.A_CONST_NULL, constExecutor);
        commandExecutorMap.put(Constants.I_CONST_M1, constExecutor);
        commandExecutorMap.put(Constants.I_CONST_0, constExecutor);
        commandExecutorMap.put(Constants.I_CONST_1, constExecutor);
        commandExecutorMap.put(Constants.I_CONST_2, constExecutor);
        commandExecutorMap.put(Constants.I_CONST_3, constExecutor);
        commandExecutorMap.put(Constants.I_CONST_4, constExecutor);
        commandExecutorMap.put(Constants.I_CONST_5, constExecutor);
        commandExecutorMap.put(Constants.L_CONST_0, constExecutor);
        commandExecutorMap.put(Constants.L_CONST_1, constExecutor);
        commandExecutorMap.put(Constants.F_CONST_0, constExecutor);
        commandExecutorMap.put(Constants.F_CONST_1, constExecutor);
        commandExecutorMap.put(Constants.F_CONST_2, constExecutor);
        commandExecutorMap.put(Constants.D_CONST_0, constExecutor);
        commandExecutorMap.put(Constants.D_CONST_1, constExecutor);

        IStoreExecutor iStoreExecutor = new IStoreExecutor();
        commandExecutorMap.put(Constants.ISTORE, iStoreExecutor);
        commandExecutorMap.put(Constants.ISTORE_0, iStoreExecutor);
        commandExecutorMap.put(Constants.ISTORE_1, iStoreExecutor);
        commandExecutorMap.put(Constants.ISTORE_2, iStoreExecutor);
        commandExecutorMap.put(Constants.ISTORE_3, iStoreExecutor);

        CommandExecutor floadExecutor = new FLoadExecutor();
        commandExecutorMap.put(Constants.F_LOAD, floadExecutor);
        commandExecutorMap.put(Constants.F_LOAD_0, floadExecutor);
        commandExecutorMap.put(Constants.F_LOAD_1, floadExecutor);
        commandExecutorMap.put(Constants.F_LOAD_2, floadExecutor);
        commandExecutorMap.put(Constants.F_LOAD_3, floadExecutor);

        BIPushExecutor biPushExecutor = new BIPushExecutor();
        commandExecutorMap.put(Constants.BI_PUSH, biPushExecutor);

        SIPushExecutor siPushExecutor = new SIPushExecutor();
        commandExecutorMap.put(Constants.SI_PUSH, siPushExecutor);

        ILoadExecutor iLoadExecutor = new ILoadExecutor();
        commandExecutorMap.put(Constants.I_LOAD, iLoadExecutor);
        commandExecutorMap.put(Constants.I_LOAD_0, iLoadExecutor);
        commandExecutorMap.put(Constants.I_LOAD_1, iLoadExecutor);
        commandExecutorMap.put(Constants.I_LOAD_2, iLoadExecutor);
        commandExecutorMap.put(Constants.I_LOAD_3, iLoadExecutor);

        ReturnExecutor returnExecutor = new ReturnExecutor();
        commandExecutorMap.put(Constants.A_RETURN, returnExecutor);
        commandExecutorMap.put(Constants.I_RETURN, returnExecutor);
        commandExecutorMap.put(Constants.L_RETURN, returnExecutor);
        commandExecutorMap.put(Constants.D_RETURN, returnExecutor);
        commandExecutorMap.put(Constants.F_RETURN, returnExecutor);
        commandExecutorMap.put(Constants.RETURN, returnExecutor);

        CommandExecutor lLoadExecutor = new LLoadExecutor();
        commandExecutorMap.put(Constants.L_LOAD, lLoadExecutor);
        commandExecutorMap.put(Constants.L_LOAD_0, lLoadExecutor);
        commandExecutorMap.put(Constants.L_LOAD_1, lLoadExecutor);
        commandExecutorMap.put(Constants.L_LOAD_2, lLoadExecutor);
        commandExecutorMap.put(Constants.L_LOAD_3, lLoadExecutor);

        CommandExecutor dLoadExecutor = new DLoadExecutor();
        commandExecutorMap.put(Constants.D_LOAD, dLoadExecutor);
        commandExecutorMap.put(Constants.D_LOAD_0, dLoadExecutor);
        commandExecutorMap.put(Constants.D_LOAD_1, dLoadExecutor);
        commandExecutorMap.put(Constants.D_LOAD_2, dLoadExecutor);
        commandExecutorMap.put(Constants.D_LOAD_3, dLoadExecutor);

        CommandExecutor aLoadExecutor = new ALoadExecutor();
        commandExecutorMap.put(Constants.A_LOAD, aLoadExecutor);
        commandExecutorMap.put(Constants.A_LOAD_0, aLoadExecutor);
        commandExecutorMap.put(Constants.A_LOAD_1, aLoadExecutor);
        commandExecutorMap.put(Constants.A_LOAD_2, aLoadExecutor);
        commandExecutorMap.put(Constants.A_LOAD_3, aLoadExecutor);

        CommandExecutor arrayLoadExecutor = new ArrayLoadExecutor();
        commandExecutorMap.put(Constants.I_A_LOAD, arrayLoadExecutor);
        commandExecutorMap.put(Constants.L_A_LOAD, arrayLoadExecutor);
        commandExecutorMap.put(Constants.F_A_LOAD, arrayLoadExecutor);
        commandExecutorMap.put(Constants.D_A_LOAD, arrayLoadExecutor);
        commandExecutorMap.put(Constants.B_A_LOAD, arrayLoadExecutor);
        commandExecutorMap.put(Constants.C_A_LOAD, arrayLoadExecutor);
        commandExecutorMap.put(Constants.S_A_LOAD, arrayLoadExecutor);
        commandExecutorMap.put(Constants.A_A_LOAD, arrayLoadExecutor);

        CommandExecutor lStoreExecutor = new LStoreExecutor();
        commandExecutorMap.put(Constants.L_STORE, lStoreExecutor);
        commandExecutorMap.put(Constants.L_STORE_0, lStoreExecutor);
        commandExecutorMap.put(Constants.L_STORE_1, lStoreExecutor);
        commandExecutorMap.put(Constants.L_STORE_2, lStoreExecutor);
        commandExecutorMap.put(Constants.L_STORE_3, lStoreExecutor);

        CommandExecutor fStoreExecutor = new FStoreExecutor();
        commandExecutorMap.put(Constants.F_STORE, fStoreExecutor);
        commandExecutorMap.put(Constants.F_STORE_0, fStoreExecutor);
        commandExecutorMap.put(Constants.F_STORE_1, fStoreExecutor);
        commandExecutorMap.put(Constants.F_STORE_2, fStoreExecutor);
        commandExecutorMap.put(Constants.F_STORE_3, fStoreExecutor);

        CommandExecutor dStoreExecutor = new DStoreExecutor();
        commandExecutorMap.put(Constants.D_STORE, dStoreExecutor);
        commandExecutorMap.put(Constants.D_STORE_0, dStoreExecutor);
        commandExecutorMap.put(Constants.D_STORE_1, dStoreExecutor);
        commandExecutorMap.put(Constants.D_STORE_2, dStoreExecutor);
        commandExecutorMap.put(Constants.D_STORE_3, dStoreExecutor);

        CommandExecutor aStoreExecutor = new AStoreExecutor();
        commandExecutorMap.put(Constants.A_STORE, aStoreExecutor);
        commandExecutorMap.put(Constants.A_STORE_0, aStoreExecutor);
        commandExecutorMap.put(Constants.A_STORE_1, aStoreExecutor);
        commandExecutorMap.put(Constants.A_STORE_2, aStoreExecutor);
        commandExecutorMap.put(Constants.A_STORE_3, aStoreExecutor);

        CommandExecutor arrayStoreExecutor = new ArrayStoreExecutor();
        commandExecutorMap.put(Constants.I_A_STORE,  arrayStoreExecutor);
        commandExecutorMap.put(Constants.L_A_STORE,  arrayStoreExecutor);
        commandExecutorMap.put(Constants.F_A_STORE,  arrayStoreExecutor);
        commandExecutorMap.put(Constants.D_A_STORE,  arrayStoreExecutor);
        commandExecutorMap.put(Constants.A_A_STORE,  arrayStoreExecutor);
        commandExecutorMap.put(Constants.B_A_STORE,  arrayStoreExecutor);
        commandExecutorMap.put(Constants.C_A_STORE,  arrayStoreExecutor);
        commandExecutorMap.put(Constants.S_A_STORE,  arrayStoreExecutor);

        CommandExecutor popExecutor = new PopExecutor();
        commandExecutorMap.put(Constants.POP,  popExecutor);
        commandExecutorMap.put(Constants.POP2,  popExecutor);

        CommandExecutor dupExecutor = new DupExecutor();
        commandExecutorMap.put(Constants.DUP,  dupExecutor);
        commandExecutorMap.put(Constants.DUP_X1,  dupExecutor);
        commandExecutorMap.put(Constants.DUP_X2,  dupExecutor);
        commandExecutorMap.put(Constants.DUP2,  dupExecutor);
        commandExecutorMap.put(Constants.DUP2_X1,  dupExecutor);
        commandExecutorMap.put(Constants.DUP2_X2,  dupExecutor);

        CommandExecutor addExecutor = new AddExecutor();
        commandExecutorMap.put(Constants.I_ADD,  addExecutor);
        commandExecutorMap.put(Constants.L_ADD,  addExecutor);
        commandExecutorMap.put(Constants.F_ADD,  addExecutor);
        commandExecutorMap.put(Constants.D_ADD,  addExecutor);

        CommandExecutor subExecutor = new SubExecutor();
        commandExecutorMap.put(Constants.I_SUB,  subExecutor);
        commandExecutorMap.put(Constants.L_SUB,  subExecutor);
        commandExecutorMap.put(Constants.F_SUB,  subExecutor);
        commandExecutorMap.put(Constants.D_SUB,  subExecutor);

        CommandExecutor mulExecutor = new MulExecutor();
        commandExecutorMap.put(Constants.I_MUL,  mulExecutor);
        commandExecutorMap.put(Constants.L_MUL,  mulExecutor);
        commandExecutorMap.put(Constants.F_MUL,  mulExecutor);
        commandExecutorMap.put(Constants.D_MUL,  mulExecutor);

        CommandExecutor divExecutor = new DivExecutor();
        commandExecutorMap.put(Constants.I_DIV,  divExecutor);
        commandExecutorMap.put(Constants.L_DIV,  divExecutor);
        commandExecutorMap.put(Constants.F_DIV,  divExecutor);
        commandExecutorMap.put(Constants.D_DIV,  divExecutor);

        CommandExecutor remExecutor = new RemExecutor();
        commandExecutorMap.put(Constants.I_REM,  remExecutor);
        commandExecutorMap.put(Constants.L_REM,  remExecutor);
        commandExecutorMap.put(Constants.F_REM,  remExecutor);
        commandExecutorMap.put(Constants.D_REM,  remExecutor);

        CommandExecutor negExecutor = new NegExecutor();
        commandExecutorMap.put(Constants.I_NEG,  negExecutor);
        commandExecutorMap.put(Constants.L_NEG,  negExecutor);
        commandExecutorMap.put(Constants.F_NEG,  negExecutor);
        commandExecutorMap.put(Constants.D_NEG,  negExecutor);
        
        CommandExecutor bitOperateExecutor = new BitOperateExecutor();
        commandExecutorMap.put(Constants.I_SHL,  bitOperateExecutor);
        commandExecutorMap.put(Constants.L_SHL,  bitOperateExecutor);
        commandExecutorMap.put(Constants.I_SHR,  bitOperateExecutor);
        commandExecutorMap.put(Constants.L_SHR,  bitOperateExecutor);
        commandExecutorMap.put(Constants.I_U_SHR,  bitOperateExecutor);
        commandExecutorMap.put(Constants.L_U_SHR,  bitOperateExecutor);
        commandExecutorMap.put(Constants.I_AND,  bitOperateExecutor);
        commandExecutorMap.put(Constants.L_AND,  bitOperateExecutor);
        commandExecutorMap.put(Constants.I_OR,  bitOperateExecutor);
        commandExecutorMap.put(Constants.L_OR,  bitOperateExecutor);
        commandExecutorMap.put(Constants.I_XOR,  bitOperateExecutor);
        commandExecutorMap.put(Constants.L_XOR,  bitOperateExecutor);
        
        CommandExecutor iIncExecutor = new IIncExecutor();
        commandExecutorMap.put(Constants.I_INC, iIncExecutor);
        
        CommandExecutor castExecutor = new CastExecutor();
        commandExecutorMap.put(Constants.I2L, castExecutor);
        commandExecutorMap.put(Constants.I2F, castExecutor);
        commandExecutorMap.put(Constants.I2D, castExecutor);
        commandExecutorMap.put(Constants.L2I, castExecutor);
        commandExecutorMap.put(Constants.L2F, castExecutor);
        commandExecutorMap.put(Constants.L2D, castExecutor);
        commandExecutorMap.put(Constants.F2I, castExecutor);
        commandExecutorMap.put(Constants.F2L, castExecutor);
        commandExecutorMap.put(Constants.F2D, castExecutor);
        commandExecutorMap.put(Constants.D2I, castExecutor);
        commandExecutorMap.put(Constants.D2L, castExecutor);
        commandExecutorMap.put(Constants.D2F, castExecutor);
        commandExecutorMap.put(Constants.I2B, castExecutor);
        commandExecutorMap.put(Constants.I2C, castExecutor);
        commandExecutorMap.put(Constants.I2S, castExecutor);
        
        CommandExecutor cmpExecutor = new CmpExecutor();
        commandExecutorMap.put(Constants.L_CMP, cmpExecutor);
        commandExecutorMap.put(Constants.F_CMP_L, cmpExecutor);
        commandExecutorMap.put(Constants.F_CMP_G, cmpExecutor);
        commandExecutorMap.put(Constants.D_CMP_L, cmpExecutor);
        commandExecutorMap.put(Constants.D_CMP_G, cmpExecutor);
        
        CommandExecutor ifExecutor = new IfExecutor();
        commandExecutorMap.put(Constants.IF_EQ, ifExecutor);
        commandExecutorMap.put(Constants.IF_NE, ifExecutor);
        commandExecutorMap.put(Constants.IF_LT, ifExecutor);
        commandExecutorMap.put(Constants.IF_GE, ifExecutor);
        commandExecutorMap.put(Constants.IF_GT, ifExecutor);
        commandExecutorMap.put(Constants.IF_LE, ifExecutor);
        commandExecutorMap.put(Constants.IF_ICMP_EQ, ifExecutor);
        commandExecutorMap.put(Constants.IF_ICMP_NE, ifExecutor);
        commandExecutorMap.put(Constants.IF_ICMP_LT, ifExecutor);
        commandExecutorMap.put(Constants.IF_ICMP_GE, ifExecutor);
        commandExecutorMap.put(Constants.IF_ICMP_GT, ifExecutor);
        commandExecutorMap.put(Constants.IF_ICMP_LE, ifExecutor);
        commandExecutorMap.put(Constants.IF_ACMP_EQ, ifExecutor);
        commandExecutorMap.put(Constants.IF_ACMP_NE, ifExecutor);
        commandExecutorMap.put(Constants.IF_NULL, ifExecutor);
        commandExecutorMap.put(Constants.IF_NON_NULL, ifExecutor);
        
        CommandExecutor putStaticExecutor = new PutStaticExecutor();
        commandExecutorMap.put(Constants.PUT_STATIC, putStaticExecutor);
        
        CommandExecutor getFieldExecutor = new GetFieldExecutor();
        commandExecutorMap.put(Constants.GET_FIELD, getFieldExecutor);
        
        CommandExecutor putFieldExecutor = new PutFieldExecutor();
        commandExecutorMap.put(Constants.PUT_FIELD, putFieldExecutor);
        
        CommandExecutor invokeVirtualExecutor = new InvokeVirtualExecutor();
        commandExecutorMap.put(Constants.INVOKE_VIRTUAL, invokeVirtualExecutor);

        CommandExecutor invokeSpecialExecutor = new InvokeSpecialExecutor();
        commandExecutorMap.put(Constants.INVOKE_SPECIAL, invokeSpecialExecutor);

        CommandExecutor invokeStaticExecutor = new InvokeStaticExecutor();
        commandExecutorMap.put(Constants.INVOKE_STATIC, invokeStaticExecutor);

        CommandExecutor invokeInterfaceExecutor = new InvokeInterfaceExecutor();
        commandExecutorMap.put(Constants.INVOKE_INTERFACE, invokeInterfaceExecutor);

        CommandExecutor newExecutor = new NewExecutor();
        commandExecutorMap.put(Constants.NEW, newExecutor);

        CommandExecutor newArrayExecutor = new NewArrayExecutor();
        commandExecutorMap.put(Constants.NEW_ARRAY, newArrayExecutor);

        CommandExecutor aNewArrayExecutor = new ANewArrayExecutor();
        commandExecutorMap.put(Constants.A_NEW_ARRAY, aNewArrayExecutor);

        CommandExecutor arrayLengthExecutor = new ArrayLengthExecutor();
        commandExecutorMap.put(Constants.ARRAY_LENGTH, arrayLengthExecutor);

        commandExecutorMap.put(Constants.CHECK_CAST, new CheckCastExecutor());

        commandExecutorMap.put(Constants.INSTANCE_OF, new InstanceOfExecutor());
        
        LDCExecutor ldcExecutor = new LDCExecutor();
        commandExecutorMap.put(Constants.LDC, ldcExecutor);
        commandExecutorMap.put(Constants.LDC_W, ldcExecutor);
        commandExecutorMap.put(Constants.LDC2_W, ldcExecutor);
        
        commandExecutorMap.put(Constants.GOTO, new GotoExecutor());

        CommandExecutor monitorExecutor = new MonitorExecutor();
        commandExecutorMap.put(Constants.MONITOR_ENTER, monitorExecutor);
        commandExecutorMap.put(Constants.MONITOR_EXIT, monitorExecutor);
    }

    /**
     * 根据指令获取指令执行器
     * 
     * @param command
     * @return
     * @throws ExecuteException
     */
    public static CommandExecutor getCommandExecutor(Command command) throws ExecuteException {
        byte commandType = command.getType();
        CommandExecutor commandExecutor = commandExecutorMap.get(commandType);
        if (commandExecutor != null) {
            return commandExecutor;
        }
        throw new ExecuteException("unknown command type!");
    }
}
