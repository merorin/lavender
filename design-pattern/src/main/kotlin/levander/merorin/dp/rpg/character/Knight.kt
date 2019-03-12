package levander.merorin.dp.rpg.character

/**
 * @author bin.guo
 * On 2019-03-12
 */
class Knight : BaseCharacter() {

    override fun fight() {
        print("Knight is ")
        this.weaponBehavior.useWeapon()
    }
}