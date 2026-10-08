import { ModificationResult } from "@/lib/types";
import { RetirementForm, UserDetails, UserForm, UserListItem, UserSearch } from "@/lib/types/management/user.model";
import "server-only"

export async function search(form : UserSearch) : Promise<UserListItem[]> {
    return dummyUsers
}

export async function findById(id : string) : Promise<UserDetails> {
    return dummyUser
}

export async function create(form: UserForm) : Promise<ModificationResult<string>> {
    return {
        result: "dummyid"
    }
}

export async function updateRetirement(id : string, form : RetirementForm) : Promise<ModificationResult<string>> {
    return {
        result : id
    }
}

const dummyUser:UserDetails = {
        id : "1",
        name : "John Doe",
        phone : "0917171711",
        email : "john.doe@example.com",
        status : "Activated",
        assignDate : "2026-10-01",
        activatedAt : "2026-10-01 12:00",
        retiredAt : "",
        contractForThisMonth: 3,
        contractTotal: 15,
        accessHistory: [
            {accessDate : "2026-10-07", signInAt : "9:00 am", signOutAt : "04:30 pm"},
            {accessDate : "2026-10-06", signInAt : "9:00 am", signOutAt : "04:30 pm"},
            {accessDate : "2026-10-05", signInAt : "9:00 am", signOutAt : "04:30 pm"},
            {accessDate : "2026-10-04", signInAt : "9:00 am", signOutAt : "04:30 pm"},
            {accessDate : "2026-10-03", signInAt : "9:00 am", signOutAt : "04:30 pm"},
        ]
}

const dummyUsers:UserListItem[] = [
    {
        id : "1",
        name : "John Doe",
        phone : "0917171711",
        email : "john.doe@example.com",
        status : "Activated",
        assignDate : "2026-10-01",
        activatedAt : "2026-10-01 12:00",
        retiredAt : ""
    }
]