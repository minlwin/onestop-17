import z from "zod"

export type UserStatus = "Pending" | "Activated" | "Retired"
export const userStatusList = ["Pending", "Activated", "Retired"]
export type UserSearchStatus = "" | UserStatus

export const userSchema = z.object({
    name : z.string().nonempty("Please enter user name"),
    phone : z.string().nonempty("Please enter phone number"),
    email : z.email("Please enter a valid email").nonempty("Please enter email address"),
    assignDate : z.string().nonempty("Please enter assign date")
})

export type UserForm = z.infer<typeof userSchema>

export const retirementSchema = z.object({
    retireDate : z.string().nonempty("Enter retire date")
})

export type RetirementForm = z.infer<typeof retirementSchema>

export interface UserSearch {
    status? : UserSearchStatus
    assignFrom? : string
    assignTo? : string
    keyword? : string
}

export interface UserListItem {
    id: string
    name: string
    phone: string
    email: string
    status: UserStatus
    assignDate: string
    activatedAt: string
    retiredAt: string 
}

export interface AccessLog  {
    accessDate : string
    signInAt : string
    signOutAt : string 
}

export type UserDetails = UserListItem & {
    contractForThisMonth : number,
    contractTotal : number
    accessHistory : AccessLog []
}