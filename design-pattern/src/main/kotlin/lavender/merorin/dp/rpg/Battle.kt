package lavender.merorin.dp.rpg

import lavender.merorin.dp.rpg.character.BaseCharacter
import lavender.merorin.dp.rpg.character.King
import lavender.merorin.dp.rpg.character.Knight
import lavender.merorin.dp.rpg.character.Queen
import lavender.merorin.dp.rpg.character.Troll
import lavender.merorin.dp.rpg.weapon.AxeBehavior
import lavender.merorin.dp.rpg.weapon.BowAndArrowBehavior
import lavender.merorin.dp.rpg.weapon.KnifeBehavior
import lavender.merorin.dp.rpg.weapon.SwordBehavior
import lavender.merorin.dp.rpg.weapon.WeaponBehavior

/**
 * using Strategy Design Pattern
 * @author bin.guo
 * On 2019-03-12
 */
object Battle {

    private val CHARACTERS : List<BaseCharacter> = listOf(King(), Queen(), Knight(), Troll())

    private val WEAPONS : List<WeaponBehavior> = listOf(AxeBehavior(), BowAndArrowBehavior(), KnifeBehavior(), SwordBehavior())

    @JvmStatic
    fun main(args : Array<String>) {
        CHARACTERS.forEach { c ->
            WEAPONS.forEach { w ->
                c.setWeapon(w)
                c.fight()
            }
        }
    }
}