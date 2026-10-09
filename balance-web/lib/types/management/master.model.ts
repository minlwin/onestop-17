import z from "zod"
import { AuditInfo } from ".."

export const subscriptionPlanSchema = z.object({
    name : z.string().nonempty("Enter plan name"),
    price: z.string().nonempty("Enter monthly fee"),
    maxEmployee: z.string().nonempty("Enter employee limit"),
    maxLedger : z.string().nonempty("Enter ledger limit"),
    maxEntry : z.string().nonempty("Enter daily entry limit"),
    description : z.string().nonempty("Enter plan description"),
    features : z.array(z.object({
        name : z.string().nonempty("Please enter feature")
    }))
})

export type SubscriptionPlanForm = z.infer<typeof subscriptionPlanSchema>

export type SubscriptionPlan = { id: number } & SubscriptionPlanForm & AuditInfo