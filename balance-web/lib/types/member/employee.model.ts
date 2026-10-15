import z from "zod"

export type EmployeeStatus = "Pending" | "Activated" | "Retired"
export const employeeStatusList : EmployeeStatus[] = ["Pending", "Activated", "Retired"]

export const employeeSchema = z.object({
    
})

export type EmployeeForm = z.infer<typeof employeeSchema>

export type EmployeeListItem = EmployeeForm & {

}

export type EmployeeDetails = EmployeeListItem & {

}
