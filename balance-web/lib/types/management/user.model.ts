export type UserStatus = "Pending" | "Activated" | "Retired"
export const userStatusList = ["Pending", "Activated", "Retired"]
export type UserSearchStatus = "" | UserStatus

export interface UserSearch {
    status? : UserSearchStatus
    createdFrom? : string
    createdTo? : string
    keyword? : string
}

export interface UserListItem {
    id: string
    name: string
    phone: string
    email: string
    status: UserStatus
    createdAt: string
    activatedAt: string
    retiredAt: string 
}