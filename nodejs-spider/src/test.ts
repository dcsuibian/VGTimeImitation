import Chance from "chance";
const chance=Chance()
console.log(chance.email({
    domain:"gmail.com"
}))
console.log(chance.phone())
console.log(chance.bool()?'男':'女')