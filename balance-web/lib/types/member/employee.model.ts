import z from "zod"
import { SelectOption } from ".."

export type EmployeeStatus = "Pending" | "Activated" | "Retired"
export const employeeStatusList : EmployeeStatus[] = ["Pending", "Activated", "Retired"]

export const permissionOptions:SelectOption[] = [
    {label : "Manager", value : "Manager"},
    {label : "Data Entry", value : "DataEntry"}
]

export const employeeSchema = z.object({
    name : z.string().nonempty("Please enter employee name"),
    phone: z.string().nonempty("Please enter employee phone"),
    email: z.email("Please enter a valid email"),
    permission: z.string().nonempty("Please select employee permission"),
    assignDate: z.string().nonempty("Please enter assign date")  
})

export type EmployeeForm = z.infer<typeof employeeSchema>

export interface EmployeeSearch {
    fromDate?: string
    toDate?: string
    keyword?: string
}

export type EmployeeListItem = EmployeeForm & {
    id: string
    name: string
    phone: string
    permission: string
    assignDate: string
    resignDate: string
    disabled: boolean
}

export type EmployeeDetails = EmployeeListItem & {
    entryCount : number
}
