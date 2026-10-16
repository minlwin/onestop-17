import { toast } from "@/components/ui/toast"
import { isRedirectError } from "next/dist/client/components/redirect-error"
import { title } from "node:process"

export { cn } from "cn"

export async function executeAction(action : () => Promise<void>) {
    try {
        await action()
    } catch(e : any) {
        if(isRedirectError(e)) {
            console.log("Redirecting")
            throw e
        }

        console.log(e)
        
        toast.add({
            title : "Message",
            description : e.message || "Unknown error"
        })
    }
}