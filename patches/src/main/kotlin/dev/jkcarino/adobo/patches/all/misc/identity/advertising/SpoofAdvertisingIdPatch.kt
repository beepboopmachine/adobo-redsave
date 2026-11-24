package dev.jkcarino.adobo.patches.all.misc.identity.advertising

import app.morphe.patcher.extensions.InstructionExtensions.getInstruction
import app.morphe.patcher.extensions.InstructionExtensions.instructions
import app.morphe.patcher.extensions.InstructionExtensions.replaceInstruction
import app.morphe.patcher.patch.patch
import app.morphe.patcher.util.getReference
import com.android.tools.smali.dexlib2.iface.instruction.FiveRegisterInstruction
import com.android.tools.smali.dexlib2.iface.instruction.OneRegisterInstruction
import com.android.tools.smali.dexlib2.iface.reference.MethodReference
import java.util.logging.Logger

@Suppress("unused")
val spoofAdvertisingIdPatch = patch(
    name = "Spoof Advertising ID",
    description = "Spoofs the device's advertising ID with a string of zeros.",
    default = false
) {
    val logger = Logger.getLogger(this::class.java.name)

    execute {
        val method = runCatching { GetInfoInternalFingerprint.method }.getOrNull()
        if (method == null) {
            logger.info("[Skipped] Google Play Services AdvertisingIdClient was not found in this app.")
            return@execute
        }

        method.apply {
            val advertisingIdClientInfo = returnType
            val advertisingIdClientInfoIndex = instructions
                .lastOrNull { instruction ->
                    val reference = instruction
                        .getReference<MethodReference>()
                        ?: return@lastOrNull false

                    reference.definingClass == advertisingIdClientInfo &&
                        reference.name == "<init>" &&
                        reference.returnType == "V"
                }
                ?.location
                ?.index
                ?: run {
                    logger.info("[Skipped] AdvertisingIdClient Info constructor was not found.")
                    return@execute
                }

            val targetRegister =
                getInstruction<FiveRegisterInstruction>(advertisingIdClientInfoIndex)
                    .registerD

            val advertisingIdIndex = instructions
                .take(advertisingIdClientInfoIndex)
                .lastOrNull { instruction ->
                    val index = instruction.location.index
                    val advertisingIdRegister =
                        (getInstruction(index) as? OneRegisterInstruction)
                            ?.registerA
                            ?: return@lastOrNull false

                    advertisingIdRegister == targetRegister
                }
                ?.location
                ?.index
                ?: run {
                    logger.info("[Skipped] AdvertisingId register was not found.")
                    return@execute
                }

            replaceInstruction(
                index = advertisingIdIndex,
                smaliInstruction = """
                    const-string v$targetRegister, "00000000-0000-0000-0000-000000000000"
                """
            )
            logger.info("[Found] Advertising ID spoofed to zeros.")
        }
    }
}
