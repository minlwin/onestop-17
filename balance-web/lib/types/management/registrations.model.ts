import z from "zod"
import { AuditInfo, PageSearch } from ".."

export type RegistrationStatus = "Pending" | "Approved"
export const registrationStatusOptions = ["Pending", "Approved"]

export interface RegistrationSearch {
    status? : RegistrationStatus | ""
    registeredFrom? : string
    registeredTo? : string
    keyword? : string
}

export type RegistrationPageSearch = RegistrationSearch & PageSearch

export interface RegistrationListItem {
    id: string
    partnerName: string
    phone : string
    email : string
    position : string
    company : string
    companyType : string
    companyAddress : string
    status : RegistrationStatus
    registeredAt : string
    approvedAt : string
}

export type RegistrationDetails = RegistrationListItem & AuditInfo

export const registrationStatusSchema = z.object({
    status : z.string().nonempty("Please select status."),
    remark : z.string()
})

export type RegistrationStatusForm = z.infer<typeof registrationStatusSchema>