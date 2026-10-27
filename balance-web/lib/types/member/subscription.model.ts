import z from "zod";

export const subscriptionSchema = z.object({
    planId : z.string().nonempty("Please select plan"),
    paymentInfoId: z.string().nonempty("Please select payment information"),
    subscriptionDate: z.string().nonempty("Please enter subscription date"),
    paymentSlip : z.file().nonoptional("Please select payment slip file")
})

export type SubscriptionForm = z.infer<typeof subscriptionSchema>