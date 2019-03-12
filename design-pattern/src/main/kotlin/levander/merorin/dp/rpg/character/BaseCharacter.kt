package levander.merorin.dp.rpg.character

import levander.merorin.dp.rpg.weapon.WeaponBehavior

/**
 * @author bin.guo
 * On 2019-03-12
 */
abstract class BaseCharacter {

    lateinit var weaponBehavior : WeaponBehavior

    abstract fun fight()

    fun setWeapon(w: WeaponBehavior) {
        this.weaponBehavior = w
    }
}