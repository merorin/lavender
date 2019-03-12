package levander.merorin.dp.rpg

import levander.merorin.dp.rpg.character.BaseCharacter
import levander.merorin.dp.rpg.character.King
import levander.merorin.dp.rpg.character.Knight
import levander.merorin.dp.rpg.character.Queen
import levander.merorin.dp.rpg.character.Troll
import levander.merorin.dp.rpg.weapon.AxeBehavior
import levander.merorin.dp.rpg.weapon.BowAndArrowBehavior
import levander.merorin.dp.rpg.weapon.KnifeBehavior
import levander.merorin.dp.rpg.weapon.SwordBehavior
import levander.merorin.dp.rpg.weapon.WeaponBehavior

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